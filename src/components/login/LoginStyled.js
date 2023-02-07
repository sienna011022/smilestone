import styled from "styled-components";

const LoginBox = styled.div`
  width: 300px;
  /* padding: 0px 10px 10px 10px; */
  margin: auto;
  display: flex;
  flex-direction: column;
`;

const LogoBox = styled.div`
  img {
    width: 100%;
  }
`;

const Id = styled.input`
  width: 100%;
  padding: 14px 0 10px 10px;
  margin: 0px 0px 20px 0px;
  border: 0;
  border-bottom: 1px solid #979797;
  :focus {
    outline: none;
  }
`;

const Password = styled.input`
  width: 100%;
  padding: 14px 0 14px 14px;
  border: 0;
  border-bottom: 1px solid #979797;
  :focus {
    outline: none;
  }
`;

const LoginBtn = styled.button`
  width: 100%;
  margin: 40px 0px 0px 0px;
  padding: 11px 0px;
  cursor: pointer;
  color: white;
  font-size: 17px;
  font-weight: 550;
  border-radius: 5px;
  border: 0;
  background-color: #ff8a3d;
  ${({ disabled }) => disabled && `background-color: #ff8a3d96;`}
  ${({ disabled }) => disabled && `cursor: default`}
`;

const Usersign = styled.div`
  padding: 35px 0px 10px 0px;
  margin-top: 93px;
  text-align: center;
  font-size: 13px;
`;

const Usersignup = styled.span`
  margin: 0px 0px 0px 10px;
  cursor: pointer;
  color: #ff8a3d;
`;

export { LoginBox, LogoBox, Id, Password, LoginBtn, Usersign, Usersignup };
