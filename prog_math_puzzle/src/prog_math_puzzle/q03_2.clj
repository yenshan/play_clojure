(ns prog-math-puzzle.q03-2)

(defn -reverse [[i d]]
  (if (= d :BACK)
    [i :FRONT]
    [i :BACK]))

(defn reverse-cards [cards n]
  (map (fn [[i flg :as dat]]
         (if (zero? (mod i n))
           (-reverse dat)
           dat))
       cards))

(let [cards (map (fn [i] [i :BACK]) (range 1 (inc 100)))]
  (->> (loop [from 2, res cards]
         (let [ncards (reverse-cards res from)]
           (if (= ncards res)
             res
             (recur (inc from) ncards))))
       (filter #(= :BACK (second %)))
       (map first)
       println))

