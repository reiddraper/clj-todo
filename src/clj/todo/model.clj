(ns todo.model)

;; ---------------------------------------------------------------------------
;; list management
;; ---------------------------------------------------------------------------

(defn create-list-state
  "Create a new atom containing the state
  for list items. Keys in the map are list
  ids"
  []
  (atom {}))

(defn get-lists
  "Return all of the lists."
  [list-ref]
  (deref list-ref))

(defn get-list
  "Get a list based on list id from list-ref.
  If the list doesn't exist, return an empty vector"
  [list-ref list-id]
  (let [l (get @list-ref list-id)]
    (if l @l
      [])))

(defn put-list!
  "put new-list as the list for list-id in list-ref"
  [list-ref list-id new-list]
  (if (get @list-ref list-id)
    (reset! (get @list-ref list-id)
          new-list)
    (swap! list-ref (fn [l] (assoc l list-id (atom new-list))))))
