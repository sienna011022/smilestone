import React, { ChangeEvent, useState } from "react";
import Modal, { IProps } from "../modal/Modal";
import { useNavigate } from "react-router-dom";
import { signupUser } from "../../apis/user";
import { Block, MainWrapper } from "./SignUpStyled";
import { AiFillCheckSquare } from "react-icons/ai";
import styled from "styled-components";
import { theme } from "../../styles/theme";

interface CProps {
  idChecked?: boolean;
  isChecked?: boolean;
}

const SignupBtn = styled.button`
  border: none;
  padding: 15px 0px;
  color: #ffff;
  font-weight: 600;
  border-radius: 5px;
  background-color: ${theme.signColor};
  cursor: pointer;
  :hover {
    background-color: #ff8a3d96;
  }
  :focus {
    outline: none;
  }
`;

const InputWrapper = styled.div<CProps>`
  margin-bottom: 20px;
  border-bottom: 1px solid
    ${(props) => (props.isChecked ? theme.signColor : "#ff0000")};
  span:nth-child(2) {
    margin-left: 10px;
    font-size: 12px;
    color: ${(props) => (props.isChecked ? theme.signColor : "#ff0000")};
  }
  input {
    width: 80%;
    flex: 1;
  }
`;

const InputIdWrapper = styled.div<CProps>`
  display: flex;
  button {
    border: 0px;
    text-align: end;
    margin-block: auto;
    color: ${(props) => (props.idChecked ? props.theme.signColor : "#ff0000")};
    font-size: 12px;
    background-color: #ffff;
    cursor: pointer;
    :focus {
      outline: none;
      border-bottom: 1px solid;
    }
    :disabled {
      cursor: default !important;
    }
  }
`;

const Signup = (props: IProps) => {
  // input state
  const navigate = useNavigate();
  const { visible, setVisible } = props;
  const [userId, setUserId] = useState("");
  const [useIdCheck, setUseIdCheck] = useState(false);
  const [password, setPassword] = useState("");
  const [usePwCheck, setUsePwCheck] = useState("");
  const [nickName, setNickName] = useState("");

  const idRegExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{5,12}$/;
  const pwRegExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,15}$/;
  const nameRegExp = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ]{0,10}$/;
  const idChecked = idRegExp.test(userId) || userId.trim() === "";
  const pwChecked = pwRegExp.test(password) || password.trim() === "";
  const pwCheckChecked = password === usePwCheck || usePwCheck.trim() === "";
  const nameChecked = nameRegExp.test(nickName) || nickName.trim() === "";
  // select state
  const [useFadeOut, setUseFadeOut] = useState(false);

  const handleId = (e: ChangeEvent<HTMLInputElement>) => {
    setUserId(e.target.value);
  };
  const handlePw = (e: ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };
  const handlePwCheck = (e: ChangeEvent<HTMLInputElement>) => {
    setUsePwCheck(e.target.value);
  };
  const handleName = (e: ChangeEvent<HTMLInputElement>) => {
    setNickName(e.target.value);
  };

  //   const handleCheckId = () => {
  //     duplicateIdCheck(userId).then((data) => {
  //       if (data.message === "EXSITING_USER") {
  //         alert("이미 아이디가 존재합니다.");
  //         return;
  //       }
  //       alert("사용 가능한 아이디 입니다.");
  //       setUseIdCheck(true);
  //     });
  //   };

  //   const handleDistrict = (value) => {
  //     setUseDistrict(value);
  //   };

  const handleSignup = () => {
    if (userId === "" || password === "") {
      return alert("아이디 또는 패스워드를 입력 해주세요");
    }

    // if (userId !== "" && !useIdCheck) {
    //   return alert("아이디 중복확인을 해주세요");
    // }

    if (!idChecked || !pwChecked) {
      return alert("아이디 또는 패스워드를 확인 해주세요");
    }
    if (password !== usePwCheck) {
      return alert("동일한 패스워드를 입력 해주세요");
    }
    if (nickName === "" || !nameChecked) {
      return alert("닉네임을 확인 해주세요");
    }

    signupUser(userId, password, nickName).then(() => {
      setUseFadeOut(true);
      setTimeout(() => {
        setVisible(false);
        setUseFadeOut(false);
      }, 500);

      console.log("회원가입 !");
        window.alert("회원가입 되었습니다. 로그인 해주세요.");
      navigate("/");
    });
  };

  return (
    <Modal
      title="sMarket 회원가입"
      visible={visible}
      setVisible={setVisible}
      useFadeOut={useFadeOut}
      // closeBtn={true}
      // width="100px"
      // height="700px"
    >
      <MainWrapper>
        <Block>
          <InputWrapper isChecked={idChecked}>
            <span>아이디</span>
            <span>영문 숫자 포함 5자 이상</span>
            <InputIdWrapper idChecked={idChecked}>
              <input
                type="text"
                value={userId}
                onChange={handleId}
                maxLength={12}
                placeholder="로그인 시 사용할 아이디를 입력 해주세요"
              />
              {/* <button
                onClick={handleCheckId}
                disabled={!idChecked || userId === ""}
              >
                중복확인
              </button> */}
            </InputIdWrapper>
          </InputWrapper>
          <InputWrapper isChecked={pwChecked}>
            <span>패스워드</span>
            <span>영문 숫자 포함 8자 이상</span>
            <input
              type="password"
              value={password}
              onChange={handlePw}
              maxLength={15}
              placeholder="로그인 시 사용할 패스워드를 입력 해주세요"
            />
          </InputWrapper>
          <InputWrapper isChecked={pwCheckChecked}>
            <span>패스워드 확인</span>
            <span>위에 입력한 패스워드와 동일</span>
            <input
              type="password"
              value={usePwCheck}
              onChange={handlePwCheck}
              placeholder="위에 입력한 패스워드와 동일하게 입력 해주세요"
            />
          </InputWrapper>
          <InputWrapper isChecked={nameChecked}>
            <span>닉네임</span>
            <span>한글 또는 영문 10자 이하</span>
            <input
              type="text"
              value={nickName}
              onChange={handleName}
              maxLength={10}
              placeholder="sMarket에서 활동할 닉네임을 입력 해주세요"
            />
          </InputWrapper>
        </Block>
        <SignupBtn onClick={handleSignup}>가입하기</SignupBtn>
      </MainWrapper>
    </Modal>
  );
};

export default Signup;
