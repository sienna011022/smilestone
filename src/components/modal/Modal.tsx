import React, { useState } from "react";
import { MdClose } from "react-icons/md";

import styled, { keyframes } from "styled-components";

export interface IProps {
  children?: JSX.Element | JSX.Element[];
  width?: string;
  height?: string;
  title?: string;
  closeBtn?: boolean;
  visible?: boolean;
  setVisible?: any;
  useFadeOut?: boolean;
  fadeOut?: boolean;
  setOpenSignup?: any;
}

const MainWrapper = styled.div<IProps>`
  @media (max-width: 690px) {
  }
  @media (min-width: 691px) and (max-width: 890px) {
    padding-top: 100px;
  }
  @media (min-width: 891px) {
    padding-top: 100px;
  }

  display: ${(props) => (props.visible ? "block" : "none")};
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
`;

const FadeInDown = keyframes`
  0% {
    opacity: 0;
    transform: translate3d(0, -20%, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }

`;
const FadeOutUp = keyframes`
  from {
    opacity: 1;
    transform: translateZ(0);
  }
  to {
    opacity: 0;
    transform: translate3d(0, -20%, 0);
  }
`;

const Container = styled.div<IProps>`
  @media (max-width: 690px) {
    width: 100%;
    height: 100%;
  }
  @media (min-width: 691px) {
    width: ${(props) => (props.width ? props.width : "600px")};
    height: ${(props) => (props.height ? props.height : "")};
    border-radius: 10px;
  }

  animation: ${(props) => (!props.fadeOut ? FadeInDown : FadeOutUp)} 0.6s;
  background-color: #fefefe;

  margin: auto;
`;

const Header = styled.div<{ color?: string }>`
  padding: 40px 40px 0px;
  font-weight: 600;
  font-size: 24px;
  color: ${(props) => props.theme.signColor};
  div {
    text-align: end;
    svg {
      cursor: pointer;
    }
  }
`;

const Content = styled.div`
  background-color: #ffff;
`;

const Modal = (props: IProps) => {
  const {
    children,
    width,
    height,
    title,
    closeBtn,
    visible,
    setVisible,
    useFadeOut,
  } = props;

  const [fadeOut, setFadeOut] = useState(false);

  const handleCancle = () => {
    setFadeOut(true);
    setTimeout(() => {
      setVisible(false);
      setFadeOut(false);
    }, 500);
  };

  return (
    <MainWrapper
      visible={visible}
      // onClick={() => handleCancle()}
    >
      <Container
        width={width}
        height={height}
        fadeOut={useFadeOut ? useFadeOut : fadeOut}
        onClick={(e) => e.stopPropagation()}
      >
        <Header>
          {!closeBtn && (
            <div>
              <MdClose size="28px" onClick={() => handleCancle()} />
            </div>
          )}
          {title && <h1>{title}</h1>}
        </Header>
        <Content>{children}</Content>
      </Container>
    </MainWrapper>
  );
};

export default Modal;
