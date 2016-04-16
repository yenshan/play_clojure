(ns pong.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def ball (atom {:x 10 :y 10 
                 :w 10 :h 10
                 :vx 1 :vy 1
                 :color java.awt.Color/BLACK
                 :draw #(.fillOval %1 %2 %3 %4 %5)
                 }))

(def bar (atom {:x 200 :y 300
                 :w 50 :h 10
                 :color java.awt.Color/BLUE
                 :draw #(.fillRect %1 %2 %3 %4 %5)
                 }))

(def walls {:left   {:x 0   :y 0   :w 1   :h 400}
            :right  {:x 390 :y 0   :w 1   :h 400}
            :top    {:x 0   :y 0   :w 400 :h 1}
            :bottom {:x 0   :y 390 :w 400 :h 1}})

(defn move-by-velocity
  [obj]
  (let [{x :x y :y vx :vx vy :vy} obj]
    (assoc obj :x (+ x vx) :y (+ y vy))))

(defn four-pos [{x :x y :y w :w h :h}] [x y (+ x w) (+ y h)])

(defn is-hit?
  [obj1 obj2]
  (let [[tx1 ty1 bx1 by1] (four-pos obj1)
        [tx2 ty2 bx2 by2] (four-pos obj2)]
    (not (or (< bx1 tx2) (< by1 ty2) (< bx2 tx1) (< by2 ty1)))))

(defn turn-if-hit
  [v obj1 obj2]
  (cond (= v 0) v
        :else (if (is-hit? obj1 obj2) (- v) v)))

(defn change-velocity
  [bal vt bar]
  (let [{v vt} bal]
    (assoc bal vt (turn-if-hit v bal bar))))

(defn move-ball!
  [bal]
  (reset! ball (-> @ball
                   (move-by-velocity)
                   (change-velocity :vy @bar)
                   (change-velocity :vy (:top walls))
                   (change-velocity :vy (:bottom walls))
                   (change-velocity :vx (:left walls))
                   (change-velocity :vx (:right walls)))))

(defn move-bar!
  [dat dr]
  (let [d (dr {:left - :right +})
        x (:x @dat)]
    (->> (d x 5)
         (assoc @dat :x)
         (reset! dat))))
      
(defn draw
  [obj g]
  (let [{x :x y :y w :w h :h} obj]
    (doto g 
      (.setColor (:color obj))
      ((:draw obj) x y w h))))

(defn main-panel 
  []
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)]
        (move-ball! ball)
        (draw @ball g)
        (draw @bar g)
        ))
    (actionPerformed [e]
      (.repaint this))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc KeyEvent/VK_LEFT) (move-bar! bar :left)
              (= kc KeyEvent/VK_RIGHT) (move-bar! bar :right))))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [panel (main-panel) 
        frame (JFrame. "Test Paint")
        timer (Timer. 10 panel)]
    (doto panel
      (.setFocusable true)
      (.addKeyListener klistn))
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400)
      (.setVisible true))
    (.start timer)))

