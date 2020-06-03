export const pageTypeReducer = (pageType = "APARTAMENT", action) => {
  if (action.type === "PAGE_TYPE") {
    return action.pageType;
  } else {
    return pageType;
  }
};

export const numberOfPagesInputReducer = (numberOfPages = 1, action) => {
  if (action.type === "NUMBER_OF_PAGES") {
    return action.numberOfPages;
  } else {
    return numberOfPages;
  }
};

export const maxSquareMetersReducer = (maxSquareMeters = 90, action) => {
  if (action.type === "MAX_METERS") {
    return action.maxSquareMeters;
  } else {
    return maxSquareMeters;
  }
};

export const minSquareMetersReducer = (minSquareMeters = 40, action) => {
  if (action.type === "MIN_METERS") {
    return action.minSquareMeters;
  } else {
    return minSquareMeters;
  }
};

export const maxSquareMeterPriceReducer = (
  maxSquareMeterPrice = 1900,
  action
) => {
  if (action.type === "MAX_PRICE") {
    return action.maxSquareMeterPrice;
  } else {
    return maxSquareMeterPrice;
  }
};

export const minSquareMeterPriceReducer = (
  minSquareMeterPrice = 1500,
  action
) => {
  if (action.type === "MIN_PRICE") {
    return action.minSquareMeterPrice;
  } else {
    return minSquareMeterPrice;
  }
};

export const startingPageReducer = (startingPage = "", action) => {
  if (action.type === "STARTING_PAGE") {
    return action.startingPage;
  } else {
    return startingPage;
  }
};

export const latestNumberOfPagesReducer = (latestNumberOfPages = 0, action) => {
  if (action.type === "LATEST_NUMBER_OF_PAGES") {
    return action.latestNumberOfPages;
  } else {
    return latestNumberOfPages;
  }
};
