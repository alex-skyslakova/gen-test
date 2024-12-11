# llm-test-gen
Generating unit tests using LLMs comparison 

## Setup
To simplify the setup, we provide a Dockerfile that contains all the necessary dependencies.
If you plan to only generate analysis for pre-generated tests (i.e., you do not need to run the test generation), you can skip this step.
[Set up the OpenAI account](https://auth.openai.com/authorize?audience=https%3A%2F%2Fapi.openai.com%2Fv1&auth0Client=eyJuYW1lIjoiYXV0aDAtc3BhLWpzIiwidmVyc2lvbiI6IjEuMjEuMCJ9&client_id=DRivsnm2Mu42T3KOpqdtwB3NYviHYzwD&device_id=9cfcaec4-f2c6-4921-af7a-7770e5d9b10f&issuer=https%3A%2F%2Fauth.openai.com&nonce=b0hGSUg3SGlhdjd0OTNQMDVyTDA3LWQ2cS5WaFBMYlNzfnlGUjA3aDVtXw%3D%3D&redirect_uri=https%3A%2F%2Fplatform.openai.com%2Fauth%2Fcallback&response_mode=query&response_type=code&scope=openid+profile+email+offline_access&state=Y1VXamEzVU9pR0JiLTlpM0YwUUtMT1o5YUdEOVlnY3FEMjc1WlRrVkxZfg%3D%3D&flow=treatment&screen_hint=signup) and generate API KEY


To build the Docker image, run the following command:
```bash
docker build -t llm-test-gen .
```

