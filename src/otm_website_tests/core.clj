(ns otm-website-tests.core
  (:require [clj-http.client :as client]))

(def base-url (or (System/getenv "OTM_BASE_URL")
                  "https://www.opentreemap.org"))
(def http-client-options {:follow-redirects false
                          :throw-exceptions false})

(defn head [relative-path]
  (client/head (str base-url relative-path) http-client-options))

(defn s3-response? [resp]
  "Determines whether the response was returned by Amazon S3 or not."
  (contains? (:headers resp) :x-amz-request-id))
