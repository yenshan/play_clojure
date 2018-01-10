;;
;; https://www.hackerrank.com/challenges/reduced-string/problem
;;
(ns hackerrank.algorithms.string.super-reduced-string)

(defn reduce-adjacent-char [string]
  (apply str 
    (loop [[a & rst] string, prev-c nil, res []]
      (if (nil? a)
        (if prev-c (conj res prev-c) res)
        (cond (= a prev-c) (recur rst nil res)
              prev-c (recur rst a (conj res prev-c))
              :else (recur rst a res))))))

(defn reduce-until-same [string]
  (loop [s string]
    (let [rs (reduce-adjacent-char s)]
      (if (= rs s)
        s
        (recur rs)))))

(let [string (read-line)
      result (reduce-until-same string)]
  (if (empty? result)
    (println "Empty String")
    (println result)))

