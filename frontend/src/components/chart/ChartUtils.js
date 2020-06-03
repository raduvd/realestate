export const parseStringToDate = (string) => {
  //Format the String from Back End to a JavaScript Date and a custom format
  string.forEach((el) => {
    var parts = el.x.split("-");
    var mydate = new Date(parts[0], parts[1] - 1, parts[2]);
    el.x = mydate;
  });
};
