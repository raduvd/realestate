import { CustomAxios } from "./axios";

const getAverage = (pageType) => {
  return CustomAxios.get("ad/average/pageType2str?pageType=" + pageType);
};

const validateTotayAds = () => {
  return CustomAxios.get("ad/validate");
};

export { getAverage, validateTotayAds };
