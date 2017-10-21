;;
;; https://www.hackerrank.com/challenges/sequence-full-of-colors/problem
;;
(ns hackerrank.clj.the-sum-of-powers)

(defn expt [x n]
  (letfn [(iter [i res]
            (if (zero? i)
              res
              (recur (dec i) (* res x))))]
    (iter n 1)))


(defn make-numbers [X N]
  (letfn [(iter [i res]
            (let [n (expt i N)]
            (if (> n X)
              res
              (recur (inc i) (conj res n)))))]
    (iter 1 '())))

  
(defn num-of-ways
  [sum numbers]
  (cond (zero? sum) 1
        (or (< sum 0) (empty? numbers)) 0
        :else (+ (num-of-ways (- sum (first numbers))
                              (rest numbers))
                 (num-of-ways sum (rest numbers)))))

(def X (Integer/parseInt (read-line)))
(def N (Integer/parseInt (read-line)))

(println (num-of-ways X (make-numbers X N)))

