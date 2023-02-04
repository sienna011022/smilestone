import { MainWrapper, TopArticle } from "./BannerStyled";
const Home = require("../../img/iPhone_Home.png");

const Banner = () => {
  return (
    <>
      <MainWrapper>
        <TopArticle>
          <section>
            <h1>
              언제든 공유가능한
              <br />
              sMarket
            </h1>
            <p>
              나만의 기술을 언제든지 공유할 수 있어요.
              <br />
              1:1 채팅, 음성 채팅, 화상 채팅으로 함께해요.
            </p>
          </section>
          <section>
            <img src={Home} alt="banner image" />
          </section>
        </TopArticle>
      </MainWrapper>
    </>
  );
};

export default Banner;
