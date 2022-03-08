(ns re-frame-todolist.db)

(def default-db

  ;; step1
  ;; {:name "re-frame"}

  ;; {:todos {1 {:id 1 :title "豚肉を買ってくる"}
  ;;          2 {:id 2 :title "たまねぎを買ってくる"}
  ;;          3 {:id 3 :title "にんじんを買ってくる"}
  ;;          4 {:id 4 :title "じゃがいもを買ってくる"}
  ;;          5 {:id 5 :title "カレーを作る"}}}

  ;; step3
  {:todos {1 {:id 1 :title "豚肉を買ってくる" :completed true}
           2 {:id 2 :title "たまねぎを買ってくる" :completed false}
           3 {:id 3 :title "にんじんを買ってくる" :completed false}
           4 {:id 4 :title "じゃがいもを買ってくる" :completed false}
           5 {:id 5 :title "カレーを作る" :completed false}}}
  )
