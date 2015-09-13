(ns otm-website-tests.core)

(def base-url (or (System/getenv "OTM_BASE_URL")
                  "http://localhost:8080"))
(def http-client-options
  "Default options for clj-http."
  {:follow-redirects false
   :throw-exceptions false})

(defn s3-response? [resp]
  "Determines whether the response was returned by Amazon S3 or not."
  (contains? (:headers resp) :x-amz-request-id))
