;;
;; https://www.hackerrank.com/challenges/sequence-full-of-colors/problem
;;
(ns hackerrank.functional-programming.recursion.seq-full-of-colors)

(defn count-ball [color sequence]
  (letfn [(iter [coll res]
            (if (empty? coll)
              res
              (if (= color (first coll))
                (recur (rest coll) (inc res))
                (recur (rest coll) res))))]
    (iter sequence 0)))

(defn same-num-of-balls? [color1 color2 sequence]
  (= (count-ball color1 sequence)
     (count-ball color2 sequence)))

(defn prefix-check [color1 color2 sequence]
  (letfn [(iter [count1 count2 coll]
            (if (empty? coll)
              true
              (cond (or (> count1 1) (> count2 1)) false
                    (= color1 (first coll)) (recur (if (zero? count2) 
                                                     (inc count1) 0)
                                                   0 
                                                   (rest coll))
                    (= color2 (first coll)) (recur 0 
                                                   (if (zero? count1)
                                                       (inc count2) 0)
                                                   (rest coll))
                    :else (recur count1 count2 (rest coll)))))]
    (iter 0 0 sequence)))


(def T (Integer/parseInt (read-line)))

(doseq [_ (range T)]
  (let [seq-dat (read-line)
        res (and (same-num-of-balls? \R \G seq-dat)
                 (same-num-of-balls? \Y \B seq-dat)
                 (prefix-check \R \G seq-dat)
                 (prefix-check \Y \B seq-dat))]
    (println (if res "True" "False"))))
