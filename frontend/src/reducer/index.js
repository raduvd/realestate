import { combineReducers } from "redux";
import * as FormReducers from "./FormReducers";
import * as ChartReducers from "./ChartReducers";

export const reducers = combineReducers({
  pageType: FormReducers.pageTypeReducer,
  numberOfPages: FormReducers.numberOfPagesInputReducer,
  apartments: ChartReducers.apartmentsReducer,
  fields: ChartReducers.fieldsReducer,
  houses: ChartReducers.housesReducer,
  apartmentFluctuations: ChartReducers.apartmentFluctuationsReducer,
  fieldFluctuations: ChartReducers.fieldFluctuationsReducer,
  houseFluctuations: ChartReducers.houseFluctuationsReducer,
  maxSquareMeters: FormReducers.maxSquareMetersReducer,
  minSquareMeters: FormReducers.minSquareMetersReducer,
  maxSquareMeterPrice: FormReducers.maxSquareMeterPriceReducer,
  minSquareMeterPrice: FormReducers.minSquareMeterPriceReducer,
  customFluctuationAverages: ChartReducers.customFluctuationAveragesReducer,
  numberOfAdsFluctuation: ChartReducers.numberOfAdsFluctuationReducer,
  startingPage: FormReducers.startingPageReducer,
  latestNumberOfPages: FormReducers.latestNumberOfPagesReducer,
});
