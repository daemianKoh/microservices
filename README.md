# Car Inventory Management & Sales
This is a simple microservices consits of car buyer, car seller and car inventory services

Short Description:
- Car buyer service can call Car inventory service to get all the car by status or buy the car
- Car seller service can call Car inventory service to add or remove car from the list
- car inventory service handle db call
- Refer to architectural diagram.jpg.

Port use:
- Car buyer services: 8000
- Car seller services: 8100
- Car Inventory: 8080
- Eureka naming server: 8761
- SpringCloud Gateway (API Gatway): 8765

List of API to call for Buyer
- GET method: http://localhost:8765/car-buyer/getListOfCars?status=all (To list all car, status can be filter by All, Available or Sold)
- POST method: http://localhost:8765/car-buyer/buyNewCar?carId=1&offerAmt=10000000 (To buy car that is Available and need to have the enough cash to buy)

List of API to call for Seller
- POST method: http://localhost:8765/car-seller/addNewCar (Refer to addNewCar.json for the request body) 
- DELETE method: http://localhost:8765/car-seller/removeCar?carId=2 (To remove car from the list)

Start the service in this sequence:
1. naming-server
2. car-inventory-service
3. car-buyer/car-seller service
4. api-gateway
