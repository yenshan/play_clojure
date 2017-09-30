(ns hackerrank.hello-world-n-time)

(defn hello_word_n_times[n]
  (when-not (zero? n)
    (println "Hello World")
    (recur (dec n))))
      
(hello_word_n_times 10)
