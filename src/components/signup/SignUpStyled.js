import styled from "styled-components";

const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 40px;
  h1 {
    font-size: 24px;
    padding-bottom: 20px;
  }
  input,
  select {
    border: none;
    padding: 15px 0px;
    :focus {
      outline: none;
    }
  }
  select {
    color: #666;
  }
`;

const Block = styled.section`
  margin-bottom: 20px;
`;

const SelectWrapper = styled.div`
  margin-bottom: 20px;
  border-bottom: 1px solid #ff8a3d;
  select {
    width: 100%;
  }
`;

export { SelectWrapper, Block, MainWrapper };
