import React, { ChangeEvent, useState, useLayoutEffect } from "react";
import styled from "styled-components";
import { theme } from "../../styles/theme";
import {
  HeaderSize,
  HeaderWrapper,
  LogoWrapper,
  SearchBarWrapper,
  SearchBar,
  NavButton,
  ChatButtonWrapper,
  ChatButton,
} from "../header/HeaderStyled";
import { IoSearchOutline } from "react-icons/io5";
import { AiOutlineMenu } from "react-icons/ai";
import { To, useNavigate, useLocation } from "react-router-dom";
import Login from "../../components/login/Login";
import Signup from "../../components/signup/SignUp";
import { IProps } from "../../components/modal/Modal";
const Logo = require("../../img/sMarketLogo.png");

interface BProps {
  isButtonClicked: Boolean;
}

const NavMenu = styled.ul<BProps>`
  @media (max-width: 890px) {
    position: absolute;
    display: flex;
    display: ${(props) => (props.isButtonClicked ? "flex" : "none")};
    top: 80px;
    right: 0;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    background-color: white;
    list-style: none;
    cursor: pointer;
    font-size: 15px;
    padding: 0;

    li {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin: auto;
      color: #4d5159;
      padding: 20px 0 20px 0;
      width: 100%;
      :hover {
        background-color: antiquewhite;
        color: ${theme.signColor};
      }
    }
  }

  @media (min-width: 891px) {
    display: flex;
    list-style: none;
    cursor: pointer;

    li {
      margin-top: 6px;
      padding: 6px;
      color: #4d5159;
      width: max-content;
    }
  }
`;

const Header = (props: IProps) => {
  const location = useLocation();

  const [isSearchFocus, setIsSearchFocus] = useState(false);
  const [isButtonClicked, setIsButtonClicked] = useState(false);
  const [useKeyword, setUseKeyword] = useState("");
  const [useOpenLogin, setUseOpenLogin] = useState(false);
  const [useOpenSignup, setUseOpenSignup] = useState(false);
  const [useOpenMypage, setUseOpenMypage] = useState(false);

  useLayoutEffect(() => {
    setIsButtonClicked(false);
  }, [location.pathname]);

  const handleSearch = (e: ChangeEvent<HTMLInputElement>) => {
    setUseKeyword(e.target.value);
  };

  const handleClick = () => {
    navigate("/search");
  };

  const navigate = useNavigate();
  const handleNavigate = (path: To) => {
    navigate(path);
  };

  return (
    <>
      <HeaderSize>
        <HeaderWrapper>
          <LogoWrapper onClick={() => handleNavigate("/")}>
            <img src={Logo} alt="logo" width="200px" />
          </LogoWrapper>
          <SearchBarWrapper>
            <SearchBar
              type="text"
              placeholder="찾고자 하는 기술명을 검색해보세요 !"
              onChange={handleSearch}
              onFocus={() => {
                setIsSearchFocus(true);
              }}
              onBlur={() => {
                setIsSearchFocus(false);
              }}
            />
            <IoSearchOutline
              onClick={() => handleClick()}
              className="magnifier"
            />
          </SearchBarWrapper>
          <NavButton
            onClick={() => {
              setIsButtonClicked((prev) => !prev);
            }}
          >
            <AiOutlineMenu style={{}} />
          </NavButton>

          <NavMenu isButtonClicked={isButtonClicked}>
            <li
              onClick={() => {
                setUseOpenLogin(true);
              }}
            >
              로그인
            </li>
            <li
              onClick={() => {
                setUseOpenSignup(true);
              }}
            >
              회원가입
            </li>

            <ChatButtonWrapper>
              <ChatButton
                onClick={() => {
                  handleNavigate("/chat");
                }}
              >
                채팅하기
              </ChatButton>
            </ChatButtonWrapper>
            {/* {user.id !== "" ? (
              <>
                <li onClick={() => handleLogout()}>로그아웃</li>
                <li onClick={() => handleNavigate("/mypage")}>마이페이지</li>
              </>
            ) : (
              <>
                <li onClick={() => setUseOpenLogin(true)}>로그인</li>
                <li onClick={() => setUseOpenSignup(true)}>회원가입</li>
              </>
            )}{" "} */}
          </NavMenu>
        </HeaderWrapper>

        {useOpenLogin && (
          <Login
            visible={useOpenLogin}
            setVisible={setUseOpenLogin}
            setOpenSignup={setUseOpenSignup}
          />
        )}
        {useOpenSignup && (
          <Signup visible={useOpenSignup} setVisible={setUseOpenSignup} />
        )}
      </HeaderSize>
    </>
  );
};

export default Header;
