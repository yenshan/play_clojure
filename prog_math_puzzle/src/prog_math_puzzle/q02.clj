(ns prog-math-puzzle.q02
  (:require [clojure.string :as s]))

;;
;; 本だとeval使ってたが、何の面白みもない。
;; 計算する部分も自前で書くと結構歯ごたえのある問題。
;;

(defn to-numlist [number]
  (loop [n number
         res []]
    (if (zero? n)
      res
      (recur (quot n 10)
             (cons (mod n 10) res)))))

(defn to-num [numlist]
  (reduce + (map * (reverse numlist) (iterate #(* % 10) 1))))

(def ops ['+ '- '* '/ 'n])

(defn op-comb [n]
  (letfn [(iter [cnt, res]
            (if (zero? cnt)
              res
              (let [cmb (for [e ops]
                          (iter (dec cnt) (conj res e)))]
                (if (> cnt 1)
                  (apply concat cmb)
                  cmb))))
          ]
    (remove #(= % (take n (repeat 'n))) (iter n []))))

(defn doop [op a1 a2]
  (condp = op
    '+ (+ a1 a2)
    '- (- a1 a2)
    '/ (if (zero? a2) nil (quot a1 a2))
    '* (* a1 a2)
    'n (+ (* a1 10) a2)))

(defn do-op-of [pred oplist numlist]
  (loop [[a & nrst] numlist
         [o & orst] oplist
         res []]
    (cond (empty? nrst) (conj res a)
          (pred o) (let [nn (doop o a (first nrst))]
                     (if nn 
                       (recur (cons nn (rest nrst))
                              orst
                              res)
                       []))
          :else (recur nrst
                       orst
                       (conj res a)))))

(defn do-all-op [oplist numlist]
  (->> numlist
       (do-op-of #(= % 'n) oplist)
       (do-op-of #(or (= % '*) (= % '/)) (remove #(= % 'n) oplist))
       (do-op-of #(or (= % '+) (= % '-)) (remove #(or (= % 'n)
                                                      (= % '*)
                                                      (= % '/))
                                                 oplist))))

(doseq [n (range 1000 10000)]
  (let [nl (to-numlist n)
        rn (to-num (reverse nl))]
    (when (= (count nl) (count (to-numlist rn)))
        (let [ops (op-comb (dec (count nl)))
              opres (map #(do-all-op % nl) ops)
              res (filter #(= (first %) rn) opres)
              ]
          (when-not (empty? res)
            (println n))))))

