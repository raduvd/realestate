export const getPageType = (pageType) => {
  return {
    type: "PAGE_TYPE",
    pageType: pageType,
  };
};

export const getNumberOfPages = (numberOfPages) => {
  return {
    type: "NUMBER_OF_PAGES",
    numberOfPages: numberOfPages,
  };
};

export const getMaxSquareMeters = (maxSquareMeters) => {
  return {
    type: "MAX_METERS",
    maxSquareMeters: maxSquareMeters,
  };
};
export const getMinSquareMeters = (minSquareMeters) => {
  return {
    type: "MIN_METERS",
    minSquareMeters: minSquareMeters,
  };
};
export const getMaxSquareMeterPrice = (maxSquareMeterPrice) => {
  return {
    type: "MAX_PRICE",
    maxSquareMeterPrice: maxSquareMeterPrice,
  };
};
export const getMinSquareMeterPrice = (minSquareMeterPrice) => {
  return {
    type: "MIN_PRICE",
    minSquareMeterPrice: minSquareMeterPrice,
  };
};

export const getStartingPage = (startingPage) => {
  return {
    type: "STARTING_PAGE",
    startingPage: startingPage,
  };
};

export const getLatestNumberOfPages = (latestNumberOfPages) => {
  return {
    type: "LATEST_NUMBER_OF_PAGES",
    latestNumberOfPages: latestNumberOfPages,
  };
};
