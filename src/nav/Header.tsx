import React, { ChangeEvent, useState } from "react";
import {
  HeaderSize,
  HeaderWrapper,
  LogoWrapper,
  SearchBarWrapper,
  SearchBar,
  NavMenu,
  ChatButtonWrapper,
  ChatButton,
} from "./HeaderStyled";
import { IoSearchOutline } from "react-icons/io5";
import { To, useNavigate } from "react-router-dom";
const Logo = require("../img/sMarketLogo.png");

const Header = () => {
  const [isSearchFocus, setIsSearchFocus] = useState(false);
  const [useKeyword, setUseKeyword] = useState("");

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

          <NavMenu>
            <li
              onClick={() => {
                handleNavigate("/login");
              }}
            >
              로그인
            </li>
            <li
              onClick={() => {
                handleNavigate("/signup");
              }}
            >
              회원가입
            </li>
          </NavMenu>

          <ChatButtonWrapper>
            <ChatButton
              onClick={() => {
                handleNavigate("/chat");
              }}
            >
              채팅하기
            </ChatButton>
          </ChatButtonWrapper>
        </HeaderWrapper>
      </HeaderSize>
    </>
  );
};

export default Header;
