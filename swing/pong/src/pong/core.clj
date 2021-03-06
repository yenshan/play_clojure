(ns pong.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent]
           [java.awt Color])
  (:gen-class))

(defn create-ball [x y]
  {:pos [x y]
   :size [10 10]
   :v [-1 1]
   :color Color/BLACK
   :draw #(.fillOval %1 %2 %3 %4 %5)
   })

(defn create-bar [x y]
  {:pos [x y]
   :size [50 10]
   :color Color/BLUE
   :draw #(.fillRect %1 %2 %3 %4 %5)
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

(def direction
  { KeyEvent/VK_LEFT [-1 0]
    KeyEvent/VK_RIGHT [1 0]})

(defn move-bar [bar dir]
  (when dir
    (assoc bar :pos
           (->> (map #(* 5 %) dir) (map + (:pos bar))))))

(defn draw
  [g {[x y] :pos [w h] :size color :color draw :draw}]
      (.setColor g color)
      (draw g x y w h))

(defn create-panel [ball bar walls]
  (proxy [JPanel ActionListener KeyListener] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (draw g @ball)
      (draw g @bar))
    ;; ActionListener
    (actionPerformed [e]
      (swap! ball move-ball (cons @bar walls))
      (.repaint this))
    ;; KeyListener
    (keyPressed [e]
       (swap! bar move-bar (get direction (.getKeyCode e))))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [ball (atom (create-ball 100 10))
        bar (atom (create-bar 200 300))
        walls (create-walls)
        panel (create-panel ball bar walls)]
    (doto panel
      (.setFocusable true)
      (.addKeyListener panel))
    (doto (JFrame. "Test Paint")
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400)
      (.setVisible true))
    (.start (Timer. 10 panel))))

