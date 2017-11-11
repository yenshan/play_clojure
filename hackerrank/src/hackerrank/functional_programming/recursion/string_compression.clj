;;
;; https://www.hackerrank.com/challenges/string-compression/problem
;; 
(ns hackerrank.functional-programming.recursion.string-compression)

(defn split-count
  ([string] (split-count (rest string) (first string) 1))
  ([string c cnt]
   (if (empty? string) 
     [[c cnt]]
     (if (= c (first string))
       (recur (rest string) c (inc cnt))
       (cons [c cnt] (split-count (rest string) (first string) 1))))))

(defn string-compress [string]
  (reduce (fn [res d]
            (if (= (second d) 1)
              (str res (first d))
              (str res (first d) (second d))))
          "" 
          (split-count string)))

(println (string-compress (read-line)))
