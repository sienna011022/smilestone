import styled from "styled-components";
// import theme from "styles/theme";

const HeaderSize = styled.div`
  position: fixed;
  top: 0;
  display: flex;
  justify-content: center;
  width: 100%;
  z-index: 1;
  font-family: "Noto Sans KR", sans-serif;
  background-color: white;
`;

const HeaderWrapper = styled.header`
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 8px;
  max-width: 1024px;
  width: 100%;
`;

const LogoWrapper = styled.div`
  display: flex;
  align-items: center;
  padding-bottom: 10px;
  font-family: "ImcreSoojin";
  color: #ff8a3d;
  cursor: pointer;
`;

const SearchBarWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-left: 1rem;
  margin-right: auto;
  padding-right: 10px;
  border-radius: 5px;
  @media (max-width: 690px) {
  }

  @media (min-width: 691px) and (max-width: 890px) {
    width: 50%;
  }

  @media (min-width: 891px) {
    width: 300px;
  }
`;

const SearchBar = styled.input`
  border: none;
  border-radius: 5px;
  margin: 8px 0 8px 10px;
  font-size: 16px;
  width: 100%;
  color: gray;

  :focus {
    outline: none;
    color: black;
  }
`;

const NavMenu = styled.ul`
  @media (max-width: 890px) {
    position: absolute;
    top: 60px;
    right: 0px;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    -webkit-box-shadow: 0px 6px 4px -4px #ff8a3d;
    -moz-box-shadow: 0px 6px 4px -4px #ff8a3d;
    box-shadow: 0px 6px 4px -4px #ff8a3d;
    border-radius: 0.3rem;
    padding: 10px 30px;
    width: 100%;
    background-color: white;
    list-style: none;
    cursor: pointer;
    font-size: 14px;

    li {
      flex-direction: column;
      margin: auto;
      color: #4d5159;
      width: max-content;
    }
  }

  @media (min-width: 891px) {
    display: flex;
    list-style: none;
    cursor: pointer;

    li {
      padding: 5px;
      color: #4d5159;
      width: max-content;
    }
  }
`;

const NavButton = styled.button`
  @media (max-width: 690px) {
    margin-left: 10px;
    border: none;
    padding-top: 5px;
    background-color: transparent;
    color: #ff8a3d;
    font-size: 30px;
    font-weight: bold;
  }

  @media (min-width: 691px) and (max-width: 890px) {
    margin-left: 80px;
    border: none;
    padding-top: 5px;
    background-color: transparent;
    color: #ff8a3d;
    font-size: 30px;
    font-weight: bold;
  }

  @media (min-width: 891px) {
    display: none;
  }
`;

const ChatButtonWrapper = styled.div`
  svg {
    position: absolute;
    top: -2px;
    right: -2px;
    color: #ff8a3d;
  }
`;

const ChatButton = styled.button`
  @media (max-width: 690px) {
    margin-left: 10px;
    height: fit-content;
  }

  @media (min-width: 691px) and (max-width: 890px) {
  }

  @media (min-width: 891px) {
  }
  margin-left: 20px;
  padding: 8px 30px;
  border: 1px solid #d0d3d8;
  border-radius: 0.3rem;
  width: 150px;
  background-color: transparent;
  color: #4d5159;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
`;

export {
  HeaderSize,
  HeaderWrapper,
  LogoWrapper,
  SearchBarWrapper,
  SearchBar,
  NavMenu,
  NavButton,
  ChatButton,
  ChatButtonWrapper,
};
