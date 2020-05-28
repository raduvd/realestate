--SIMPLE AVERAGES
create view apartmentDateAndPrice
as select p.addedAtDate, p.squareMeterPrice
from adprice p left join ad a
ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType='APARTAMENT' and a.state='VALID';

create view apartmentAverages as select avg(squareMeterPrice) as squareMeterPriceAverage, addedAtDate
from apartmentDateAndPrice group by (addedAtDate);


create view houseDateAndPrice
as select p.addedAtDate, p.squareMeterPrice
from adprice p left join ad a
ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType='CASE' and a.state='VALID';

create view houseAverages as select avg(squareMeterPrice) as squareMeterPriceAverage, addedAtDate
from houseDateAndPrice group by (addedAtDate);


create view fieldDateAndPrice
as select p.addedAtDate, p.squareMeterPrice
from adprice p left join ad a
ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType='TEREN' and a.state='VALID';

create view fieldAverages as select avg(squareMeterPrice) as squareMeterPriceAverage, addedAtDate
from fieldDateAndPrice group by (addedAtDate);

