import styled from "styled-components";

const MainWrapper = styled.main`
  @media screen and (max-width: 575px) {
    article {
      flex-direction: column;
      text-align: center;
      padding-top: 30px;

      h1 {
        font-size: 34px;
      }
      p {
        padding-bottom: 2rem;
      }
      img {
        width: 300px;
        margin-bottom: 30px;
      }
    }
  }

  @media (min-width: 576px) and (max-width: 991px) {
    article {
      flex-direction: column;
      text-align: center;
      padding-top: 30px;

      h1 {
        font-size: 34px;
      }
      p {
        padding-bottom: 2rem;
      }
      img {
        width: 450px;
        margin-bottom: 30px;
      }
    }
  }

  @media (min-width: 992px) {
    article {
      flex-direction: row;
      justify-content: space-evenly;

      h1 {
        font-size: 40px;
      }
      img {
        width: 500px;
      }
    }
  }

  article {
    display: flex;
    background-color: #f9f7f2;
    h1 {
      font-weight: bold;
    }
    p {
      color: #818180;
    }
  }
`;

const TopArticle = styled.article`
  section:nth-child(1) {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
`;

export { MainWrapper, TopArticle };
