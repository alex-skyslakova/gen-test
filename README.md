# GenTest
Generating unit tests using LLMs comparison 

## Prerequisities
To simplify the setup, we provide a Dockerfile that contains all the necessary dependencies.
Therefore, you need to have [Docker](https://docs.docker.com/get-started/introduction/get-docker-desktop/) installed on your machine. 

Based on the actions you plan to perform, you may need to set up accounts and billing for each of the three used models. 

## Setup
GenTest provides three actions that can be performed:
- **Present saved results** (default): If you only want to see the results of the analysis on pre-generated tests, you can just visualize plots from saved data.
- **Run analysis on saved generated tests** To avoid generating new tests, either due to time or avoiding costs, you can run the analysis on pre-generated tests. The tests will be executed, all values will be computed and saved in the `output` folder. 
- **Generate new tests**: If you want to generate new tests, you need to set up the OpenAI, Google Gemini, and DeepSeek accounts and the billing, since the tool is configured for using paid models. Once the tests are generated, analysis will be executed in the same manner as in the previous mode.

### Present saved results
- this is the default action
-  run this docker command to visualize the results:
```bash
 # first, build the docker image
 docker build -t gen-test .
 # create a folder for the output
 mkdir my_output
 # then, run the docker image 
 docker run -d --name my_container -it gen-test
 # copy the output folder from the container to the host
 docker cp my_container:/app/output my_output
 
```
- you can find the plots in the `my_output` folder

### Run analysis on saved generated tests
- open .env file and set `ACTION=2`
- you can also update the amount of data to be analyzed by setting `DATA_SIZE` variable in the .env file. The default value is full size of the saved dataset 200. However, the analysis takes few hours for full dataset. For testing purposes, set `DATA_SIZE=10` to analyze only 10 tests.
- run the following commands:
```bash
 # first, build the docker image
 docker build -t gen-test .
 # create a folder for the output
 mkdir my_output
 # then, run the docker image 
 docker run -d --name my_container -it gen-test
 # copy the output folder from the container to the host
 docker cp my_container:/app/output my_output
 
```
- note: analysis may take a while (up to multiple hours), depending on the number of tests

### Generate new tests and run analysis 
- [Set up the OpenAI account](https://auth.openai.com/authorize?audience=https%3A%2F%2Fapi.openai.com%2Fv1&auth0Client=eyJuYW1lIjoiYXV0aDAtc3BhLWpzIiwidmVyc2lvbiI6IjEuMjEuMCJ9&client_id=DRivsnm2Mu42T3KOpqdtwB3NYviHYzwD&device_id=9cfcaec4-f2c6-4921-af7a-7770e5d9b10f&issuer=https%3A%2F%2Fauth.openai.com&nonce=b0hGSUg3SGlhdjd0OTNQMDVyTDA3LWQ2cS5WaFBMYlNzfnlGUjA3aDVtXw%3D%3D&redirect_uri=https%3A%2F%2Fplatform.openai.com%2Fauth%2Fcallback&response_mode=query&response_type=code&scope=openid+profile+email+offline_access&state=Y1VXamEzVU9pR0JiLTlpM0YwUUtMT1o5YUdEOVlnY3FEMjc1WlRrVkxZfg%3D%3D&flow=treatment&screen_hint=signup) and generate API KEY
- [Set up the Google Gemini account](https://aistudio.google.com/apikey?_gl=1*hra5cc*_ga*MzYyMzU1NDIuMTcyNTE5MDA5Mw..*_ga_P1DBVKWT6V*MTczNDIxNDcyMy45LjEuMTczNDIxNDczNS4wLjAuNTczMDg3Mzgw) and generate API KEY
- [Set up the DeepSeek account](https://platform.deepseek.com/api_keys) and generate API KEY
- To be able to generate new tests, you also need to enable pay-as-you-go or pre-pay credits on the platforms. 
- open .env file and set `ACTION=3`
- you can also update the amount of data to be analyzed by setting `DATA_SIZE` variable in the .env file.
- run the following commands:
```bash
 # first, build the docker image
 docker build -t gen-test .
 # create a folder for the output
 mkdir my_output
 # then, run the docker image 
 docker run -d --name my_container -it gen-test
 # copy the output folder from the container to the host
 docker cp my_container:/app/output my_output
 
```


