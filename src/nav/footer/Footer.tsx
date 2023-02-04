import {
  FooterSize,
  FooterWrap,
  FooterWrapper,
  LinkToGitWrapper,
  License,
} from "../footer/FooterStyled";
const Logo = require("../../img/sMarketLogo.png");

interface IMemberProps {
  name: string;
  stack: string;
  githubId: string;
}

const member: Array<IMemberProps> = [
  {
    name: "이다혜",
    stack: "Front-End",
    githubId: "leedahye2001",
  },
  {
    name: "김성윤",
    stack: "Back-End",
    githubId: "Sienna011022",
  },
  {
    name: "남정진",
    stack: "Back-End",
    githubId: "JeongJin984",
  },
  {
    name: "김경욱",
    stack: "iOS",
    githubId: "KimKU1018",
  },
  {
    name: "김근범",
    stack: "Android",
    githubId: "agfalcon",
  },
];

const Footer = () => {
  // const location = useLocation();

  return (
    <>
      <FooterSize>
        <FooterWrap>
          <FooterWrapper>
            <img src={Logo} alt="logo" width="200px" height="100px" />
            <LinkToGitWrapper>
              {member.map(({ name, stack, githubId }) => (
                <div>
                  <h2>
                    {name} {stack}
                  </h2>
                  <h3>
                    👉 GitHubLink
                    <a href={`https://github.com/${githubId}`}> {githubId}</a>
                  </h3>
                </div>
              ))}
            </LinkToGitWrapper>
          </FooterWrapper>
          <License>
            <p>Copyrightⓒ2023 sMilestone All rights reserved.</p>
          </License>
        </FooterWrap>
      </FooterSize>
    </>
  );
};

export default Footer;
