;;
;; https://www.hackerrank.com/challenges/fp-hello-world-n-times/problem
;;
(ns hackerrank.functional-programming.introduction.hello-world-n-time)

(def n (Integer/parseInt (read-line)))

(defn hello_word_n_times[n]
  (when-not (zero? n)
    (println "Hello World")
    (recur (dec n))))
      
