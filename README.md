# theGuarantorsRestAPIAutomation

- RestAPI Automation using HTTP Client. 

Test Setup :  Since this service is just adding validation to the node application.

The endpoint https://research.theguarantors.com/qa-test/ Can be tested with Postman. 

Invalid input should return a 400 Bad Request response with the body object like the following : 

SyntaxError: Unexpected token l in JSON at position 17

    at JSON.parse (<anonymous>)
    at parse (/srv/qa-test/node_modules/body-parser/lib/types/json.js:89:19)
    at /srv/qa-test/node_modules/body-parser/lib/read.js:121:18
    at invokeCallback (/srv/qa-test/node_modules/raw-body/index.js:224:16)
    at done (/srv/qa-test/node_modules/raw-body/index.js:213:7)
    at IncomingMessage.onEnd (/srv/qa-test/node_modules/raw-body/index.js:273:7)
    at IncomingMessage.emit (events.js:180:13)
    at endReadableNT (_stream_readable.js:1106:12)
    at process._tickCallback (internal/process/next_tick.js:178:19)
    
Test Cases :     

1.Validate the sorting service returns sorted list of numbers in asending order with valid input. 

2. validate the sorting service without header content type 

3.validate the sorting service without payloads

4. validate the sorting service with invalid input 

5. validate the sorting service with string, negative, decimal elements in a list , returns a list of elements in ascending order 

6.validate the sorting service with more than one key value pair Jason payloads 

7. validating the sorting service returns large integer as exponential function 

8. validate the sorting service with empty array 

9. Validate the sorting service request query parameters not configured properly 

  


 




