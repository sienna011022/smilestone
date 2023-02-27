import styled from "styled-components";

const FooterSize = styled.div`
  padding: 80px 8px;
  background-color: #f7f7f7;
  z-index: 0;
`;

const FooterWrap = styled.div`
  margin: auto;
  @media (max-width: 690px) {
  }

  @media (min-width: 691px) and (max-width: 890px) {
  }

  @media (min-width: 891px) {
    max-width: 1024px;
    min-width: 891px;
  }
`;

const FooterWrapper = styled.div`
  display: flex;
  align-items: center;
  border-bottom: 1px solid silver;
  width: 100%;
  flex-direction: column;

  @media (max-width: 690px) {
    flex-direction: column;
    align-items: center;
  }
`;

const LinkToGitWrapper = styled.div`
  font-size: 12px;
  /* background-color: antiquewhite; */

  @media (max-width: 690px) {
    flex-direction: row;
  }

  div {
    float: left;
    text-align: center;
    padding: 0 8px 0 8px;
    h3 {
      padding-bottom: 10px;
      font-size: 15px;
      font-weight: 400;
      color: #ff8a3d;
      a {
        color: #ff8a3d;
        text-decoration: none;
        font-weight: bolder;
        :hover {
          text-decoration: underline;
        }
      }
    }
  }
`;

const License = styled.div`
  padding: 10px 0;
  text-align: center;
`;

export { FooterSize, FooterWrap, FooterWrapper, LinkToGitWrapper, License };
