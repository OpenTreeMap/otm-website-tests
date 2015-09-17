(ns otm-website-tests.faq-test
  (:use midje.sweet)
  (:use [otm-website-tests.core]))

(facts "about /faq"

  (fact "it returns a response from S3 if trailing slash exists"
    (let [resp (head "/faq/")]
      (resp :status) => 200
      (s3-response? resp) => true))

  (fact "it returns a redirect if trailing slash is missing"
    (let [resp (head "/faq")]
      (resp :status) => 302
      (get-in resp [:headers :location]) => "/faq/")))
