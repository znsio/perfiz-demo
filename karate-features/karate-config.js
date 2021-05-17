function fn() {
  var env = karate.env;
  karate.log('karate.env system property was:', env);
  if (!env) {
    karate.log('Setting env to dev.');
    env = 'dev';
  }
  var config = {
    urlBase: 'http://host.docker.internal:9999'
  };
  if (env == 'stage') {
    config.urlBase = 'https://stage-host/v1/auth';
  } else if (env == 'e2e') {
    config.urlBase = 'https://e2e-host/v1/auth';
  }
  karate.configure('connectTimeout', 500);
  karate.configure('readTimeout', 500);
  return config;
}