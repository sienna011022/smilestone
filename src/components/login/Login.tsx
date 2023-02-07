// modules
import React, { useState, useContext } from "react";
import { loginUser } from "../../apis/user.js";
import { UserDispatchContext } from "../../context/context";
import { useNavigate } from "react-router-dom";
// components
import Modal, { IProps } from "../../components/modal/Modal";
// styles
import {
  LoginBox,
  LogoBox,
  Id,
  Password,
  LoginBtn,
  Usersign,
  Usersignup,
} from "./LoginStyled";
const Logo = require("../../img/sMarketLogo.png");

const Login = (props: IProps) => {
  const { visible, setVisible, setOpenSignup } = props;
  const dispatch = useContext(UserDispatchContext);
  // const [user, setUser] = userRecoilState(userState);
  const navigate = useNavigate();

  const [userId, setId] = useState("");
  const [password, setPassword] = useState("");
  const [useSave, setUseSave] = useState(false);
  const [useFadeOut, setUseFadeOut] = useState(false);

  const goToSignup = () => {
    setUseFadeOut(true);
    setTimeout(() => {
      setVisible(false);
      setUseFadeOut(false);
      setOpenSignup(true);
    }, 500);
  };

  const handleLogin = () => {
    loginUser(userId, password).then((result) => {
      const { message, token, user } = result;
      if (message === "INVALID_USER") {
        alert("아이디 또는 비밀번호가 잘못 되어있습니다.");
      } else if (message === "SUCCESS_LOGIN") {
        if (useSave) {
          localStorage.setItem("token", token);
        } else {
          sessionStorage.setItem("token", token);
        }
      }
      dispatch({
        type: "LOGIN",
        payload: {
          userId: user.userId,
          nickName: user.nickName,
        },
      });
      setUseFadeOut(true);
      setTimeout(() => {
        setVisible(false);
        setUseFadeOut(false);
      }, 500);
      navigate("/");
    });
  };

  const handleIdInput = (e: {
    target: { value: React.SetStateAction<string> };
  }) => {
    setId(e.target.value);
  };

  const handlePwInput = (e: {
    target: { value: React.SetStateAction<string> };
  }) => {
    setPassword(e.target.value);
  };

  const handleEnter = (e: { keyCode: number }) => {
    if (e.keyCode === 13) {
      handleLogin();
    }
  };
  const isValidButton = isValidId(userId) && isValidPw(password);

  function isValidId(str: string) {
    const regId = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{5,12}$/;
    return regId.test(str);
  }

  function isValidPw(str: string) {
    const regPw = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,15}$/;
    return regPw.test(str);
  }

  return (
    <Modal
      width="365px"
      visible={visible}
      setVisible={setVisible}
      useFadeOut={useFadeOut}
    >
      <LoginBox>
        <LogoBox>
          <img src={Logo} alt="logo" />
        </LogoBox>
        <div>
          <Id
            onChange={handleIdInput}
            onKeyDown={handleEnter}
            type="text"
            placeholder="아이디를 입력하세요"
            id="userId"
            name="userId"
            required
          />
          <Password
            onChange={handlePwInput}
            onKeyDown={handleEnter}
            type="password"
            placeholder="비밀번호를 입력하세요"
            id="password"
            name="password"
            required
          />
        </div>
        <div>
          <LoginBtn disabled={!isValidButton} onClick={handleLogin}>
            로그인
          </LoginBtn>
        </div>
        <Usersign>
          <span>아직 회원이 아니신가요?</span>
          <Usersignup onClick={goToSignup}>회원가입</Usersignup>
        </Usersign>
      </LoginBox>
    </Modal>
  );
};

export default Login;
