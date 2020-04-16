(ns codewars.sumdigpow)

(defn digvec [n]
  (if (= n 0)
    []
    (cons (mod n 10) (digvec (quot n 10)))))

(defn pow [n p]
  (apply * (repeat p n)))

(defn sum-pow [n]
  (let [da (reverse (digvec n))]
    (loop [[x & xs] da
           p 1
           res 0]
      (if (= x nil)
        res
        (recur xs (inc p) (+ res (pow x p)))))))

(defn sum-dig-pow [a b]
  (for [x (range a (inc b))
        :when (= x (sum-pow x))]
    x))

(sum-dig-pow 1 100)
