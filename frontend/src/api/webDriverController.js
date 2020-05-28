import { CustomAxios } from "./axios";

const populateDB = (numberOfPages, pageType) => {
  CustomAxios.get(
    "webdriver/" + numberOfPages + "/pageType2str?pageType=" + pageType
  );
};

export { populateDB };
