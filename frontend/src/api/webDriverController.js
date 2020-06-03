import { CustomAxios } from "./axios";

export const populateDB = (startingPage, numberOfPages, pageType) => {
  startingPage = encodeURIComponent(startingPage.replace(/\//g, "TTT"));
  if (!startingPage || 0 === startingPage.length) {
    startingPage = "null";
  }
  CustomAxios.get(
    "webdriver/" +
      startingPage +
      "/" +
      numberOfPages +
      "/pageType2str?pageType=" +
      pageType
  );
};

export const populateNumberOfAds = () => {
  CustomAxios.get("webdriver/numberOfAds");
};



