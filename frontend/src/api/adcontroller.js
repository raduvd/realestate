import { CustomAxios } from "./axios";

export const getCustomFluctuationAverage = (
  pageType,
  maxSquareMeters,
  minSquareMeters,
  maxSquareMeterPrice,
  minSquareMeterPrice
) => {
  return CustomAxios.get(
    "ad/custom/fluctuation/average/" +
      maxSquareMeters +
      "/" +
      minSquareMeters +
      "/" +
      maxSquareMeterPrice +
      "/" +
      minSquareMeterPrice +
      "/pageType2str?pageType=" +
      pageType
  );
};

export const getNumberOfAdsFluctuationFromBackEnd = () => {
  return CustomAxios.get("ad/numberOfAds");
};

export const getAverage = (pageType) => {
  return CustomAxios.get("ad/average/pageType2str?pageType=" + pageType);
};

export const getFluctuationAverage = (pageType) => {
  return CustomAxios.get(
    "ad/fluctuation/average/pageType2str?pageType=" + pageType
  );
};

export const validateTotayAds = () => {
  return CustomAxios.get("ad/validate");
};

export const getLatestNumberOfPagesFromBE = () => {
  return CustomAxios.get("ad/latestNumberOfAds");
};
