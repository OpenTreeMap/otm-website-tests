(defproject otm-website-tests "0.1.0-SNAPSHOT"
  :description "An automated test suite for the OpenTreeMap website."
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http "2.0.0"]]
  :profiles {:dev {:dependencies [[midje "1.7.0"]]
                   :plugins [[lein-midje "3.1.3"]]}})
