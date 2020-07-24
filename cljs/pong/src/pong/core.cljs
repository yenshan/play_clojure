(ns pong.core)

(defn create-ball [x y]
  {:pos [x y]
   :size [10 10]
   :color [255 255 255]
   :v [-1 1]
   })

(defn create-bar [x y]
  {:pos [x y]
   :size [50 10]
   :color [0 0 255]
   :dv [1 -1] 
   })

(defrecord Wall [pos size dv])
(defn create-walls []
  [(->Wall [0 0]   [1 400] [-1 1]) ; Left
   (->Wall [400 0] [1 400] [-1 1]) ; Right
   (->Wall [0 0]   [400 1] [1 -1]) ; Top
   (->Wall [0 400] [400 1] [1 -1])]) ; Bottom

(defn move-by-velocity
  [{:keys [pos v] :as obj}]
  (assoc obj :pos (map + pos v)))

(defn rect-hit? [[tx1 ty1 bx1 by1] [tx2 ty2 bx2 by2]]
  (not (or (< bx1 tx2) (< by1 ty2) (< bx2 tx1) (< by2 ty1))))

(defn hit? [obj1 obj2]
  (letfn [(->rect [{[x y] :pos [w h] :size}] [x y (+ x w) (+ y h)])]
    (rect-hit? (->rect obj1) (->rect obj2))))

(defn some-hit [bal col] 
  (some #(if (hit? bal %) %) col))

(defn turn-dir [bal obj]
  (assoc bal :v (map * (:v bal) (:dv obj))))

(defn move-ball [ball objs]
  (let [ball' (move-by-velocity ball)
        obj (some-hit ball' objs)]
    (if (nil? obj)
      ball' 
      (turn-dir ball obj))))


(defn move-bar [{pos :pos :as bar} dir]
  (assoc bar :pos (map + pos dir)))

(defn get-canvas-context-from-id
  [id]
  (let [canvas (.getElementById js/document id)]
    {:canvas canvas
     :ctx (.getContext canvas "2d")
     :size [(.-width canvas) (.-height canvas)]
     }))

(defn to-color [rgbas]
  (str "rgb("
       (apply str (interpose "," rgbas))
       ")"))

(defn draw-rect
  [ctx x y w h color]
    (doto ctx
      (aset "fillStyle" (to-color color))
      (.fillRect x y w h)))

(defn draw-obj
  [ctx {[x y] :pos [w h] :size color :color}]
  (draw-rect ctx x y w h color))

(defn clear-screen
  [{ctx :ctx [w h] :size}]
  (draw-rect ctx 0 0 w h [0 0 0]))

(def canvas-info (get-canvas-context-from-id "canvas"))
(def walls (create-walls))
(def ball (atom (create-ball 100 10)))
(def bar (atom (create-bar 200 300)))


(defn game-loop []
  (let [_ (swap! ball move-ball (cons @bar walls))
        ctx (:ctx canvas-info)]
    (clear-screen canvas-info)
    (draw-obj ctx @ball)
    (draw-obj ctx @bar)))

(defn handle-key [e]
  (case (.-keyCode e)
  37 (swap! bar move-bar [-5 0]) ; Key Left
  39 (swap! bar move-bar [5 0])  ; Key Right
  ))

 
(aset js/document "onkeydown" handle-key)

(js/setInterval game-loop 20)

