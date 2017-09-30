#!/usr/bin/env clj

(ns hackerrank.hello-world-n-time)

(def n (Integer/parseInt (read-line)))

(defn hello_word_n_times[n]
  (when-not (zero? n)
    (println "Hello World")
    (recur (dec n))))
      
(hello_word_n_times n)
