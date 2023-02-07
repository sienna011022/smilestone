import styled from "styled-components";
import { theme } from "../../styles/theme";

const HeaderSize = styled.div`
  position: sticky;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  width: 100%;
  z-index: 1;
  background-color: white;
`;

const HeaderWrapper = styled.header`
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 10px;
  max-width: 1024px;
  width: 100%;
`;

const LogoWrapper = styled.div`
  display: flex;
  align-items: center;
  padding-bottom: 10px;
  cursor: pointer;
`;

const SearchBarWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-left: 1rem;
  margin-right: auto;
  padding-right: 10px;
  background-color: #ececec;
  border-radius: 15px;

  .magnifier {
    margin-right: 10px;
    width: 30px;
    :hover {
      cursor: pointer;
    }
  }

  @media (min-width: 320px) and (max-width: 480px) {
  }

  @media (max-width: 690px) {
    width: 300px;
  }

  @media (min-width: 691px) and (max-width: 890px) {
    width: 350px;
  }

  @media (min-width: 891px) {
    width: 400px;
  }
`;

const SearchBar = styled.input`
  border: none;
  border-radius: 5px;
  margin: 10px 0 10px 10px;
  padding-left: 10px;
  font-size: 18px;
  width: 100%;
  background-color: #ececec;
  color: gray;

  :focus {
    outline: none;
    color: black;
  }
`;

const NavButton = styled.button`
  @media (max-width: 690px) {
    margin-left: 10px;
    border: none;
    padding-top: 5px;
    background-color: transparent;
    color: ${theme.signColor};
    font-size: 30px;
    font-weight: bold;
  }

  @media (min-width: 691px) and (max-width: 890px) {
    margin-left: 80px;
    border: none;
    padding-top: 5px;
    background-color: transparent;
    color: ${theme.signColor};
    font-size: 30px;
    font-weight: bold;
  }

  @media (min-width: 891px) {
    display: none;
  }
`;

const ChatButtonWrapper = styled.div``;

const ChatButton = styled.button`
  @media (max-width: 890px) {
    margin: 10px 0 20px 0;
    width: 120px;
    font-size: 15px;
  }

  @media (min-width: 891px) {
  }

  margin: 0 20px 0 20px;
  padding: 10px 30px;
  border: 1px solid ${theme.signColor};
  border-radius: 2rem;
  width: 120px;
  background-color: transparent;
  color: ${theme.signColor};
  font-size: 16px;
  font-weight: 700;
  cursor: pointer;
  :hover {
    background-color: ${theme.signColor};
    color: white;
  }
`;

export {
  HeaderSize,
  HeaderWrapper,
  LogoWrapper,
  SearchBarWrapper,
  SearchBar,
  NavButton,
  ChatButton,
  ChatButtonWrapper,
};
