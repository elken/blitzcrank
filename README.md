# blitzcrank [![Clojars Project](https://img.shields.io/clojars/v/blitzcrank.svg)](https://clojars.org/blitzcrank) [![Build Status](https://travis-ci.org/elken/blitzcrank.svg?branch=master)](https://travis-ci.org/elken/blitzcrank)

A Clojure implementation of the public [League of Legends API](https://developer.riotgames.com/). No access to tournament API yet.

# Usage
Add the library to your project as below:

### Leiningen/Boot
```
[blitzcrank "0.1.0"]
```
### Gradle
```groovy
compile "blitzcrank:blitzcrank:0.1.0"
```
### Maven
```xml
<dependency>
  <groupId>blitzcrank</groupId>
  <artifactId>blitzcrank</artifactId>
  <version>0.1.0</version>
</dependency>
```

API methods are located in the `blitzcrank.endpoints>` namespace.

### Example
```clojure
user=> (require '[blitzcrank.endpoints.summoner :as summoner])
nil
user=> (summoner/get-summoner-by-name "Instadrone" {:api-key "AVALIDKEY"})
Attempting to get https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/Instadrone
{:id 44040660, :accountId 37951631, :name "Instadrone", :profileIconId 1470, :revisionDate 1496956028000, :summonerLevel 30}

```
## Feature parity
| Feature| Status| Commit |
| -------- | -------- |---- |
| [champion-mastery-v3](https://developer.riotgames.com/api-methods/#champion-mastery-v3)   | Not yet implemented  | |
| [champion-v3](https://developer.riotgames.com/api-methods/#champion-v3) | Not yet implemented | |
| [league-v3](https://developer.riotgames.com/api-methods/#league-v3) | Not yet implemented | |
| [static-data-v3](https://developer.riotgames.com/api-methods/#lol-static-data-v3) | Not yet implemented | |
| [lol-status-v3](https://developer.riotgames.com/api-methods/#lol-status-v3) | [Implemented](https://github.com/elken/blitzcrank/blob/master/src/blitzcrank/api/v3/status.clj) | [aae08dc](https://github.com/elken/blitzcrank/commit/aae08dcfa2746106b00d0979e0e9f05e097e065f) |
| [masteries-v3](https://developer.riotgames.com/api-methods/#masteries-v3) | [Implemented](https://github.com/elken/blitzcrank/blob/master/src/blitzcrank/api/v3/masteries.clj) | [7a4610e](https://github.com/elken/blitzcrank/commit/7a4610e03fbb3fa643b5d3c0f74e1e6a75f94b04) |
| [match-v3](https://developer.riotgames.com/api-methods/#match-v3) | Not yet implemented | |
| [runes-v3](https://developer.riotgames.com/api-methods/#runes-v3) | Not yet implemented | |
| [spectator-v3](https://developer.riotgames.com/api-methods/#spectator-v3) | Not yet implemented | |
| [summoner-v3](https://developer.riotgames.com/api-methods/#summoner-v3) | [Implemented](https://github.com/elken/blitzcrank/blob/master/src/blitzcrank/api/v3/summoner.clj) | [4f02eab](https://github.com/elken/blitzcrank/commit/4f02eab23fcdbe87c160503b5333de7358525fd5#diff-63c71c866bf369dee4124a3ab90fc8e3) |

## Config

Default configuration can be provided by supplying a `config.edn` file within a resource-path 
(the top-level `resources` directory is provided) by default. Further paths can be adding by adding a `:profile` map to
`project.clj` as below:
```clojure
:profiles {:prod {:resource-paths ["config/prod"]}
           :dev  {:resource-paths ["config/dev"]}}
```

Currently supported options are:

### `api-key` 

Primarily used for testing, but can also be used to simplify the application. Every call to an API method will attempt 
to read this value if no argument is passed.

### `region`

Default region to use for API calls. As before, every call to an API method can be called with a region. Setting this 
to your largest projected userbase would be a sensible idea.

### `version`

Currently, this library only supports version 3 of the API, but this field has been added if the API were to mutate.

## Development

Run in a REPL with `lein repl`. 

### Documentation
API documentation is [available](https://elken.github.io/blitzcrank/) and 
generated using [codox](https://github.com/weavejester/codox). Generate using `lein codox`, which by default will output
the docs to `../docs`. <!-- Need to get rid of this ASAP -->

## License

Copyright © 2017 Ellis Kenyő

Distributed under the BSD 3 Clause.
