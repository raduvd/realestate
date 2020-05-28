import React from "react";
import { BrowserRouter, Route } from "react-router-dom";
import Header from "./reusables/Header";
import ChartList from "./chart/chartlist";
import Populate from "./populate/Populate";

export const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <Route path="/" exact component={ChartList} />
      <Route path="/populate" exact component={Populate} />
    </BrowserRouter>
  );
};
