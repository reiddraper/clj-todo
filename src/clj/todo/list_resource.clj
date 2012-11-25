(ns todo.list-resource
  (:use [compojure.core :as compojure :only [ANY routes]])
  (:require compojure.route
            [liberator.core :as liberator]
            [todo.formatters :as formatters]
            [todo.model :as model]))

(defn handle-ok
  [req]
  (let [list-id (get-in req [:request :list-id])
        list-ref (get-in req [:request :state])
        list-contents (model/get-list list-ref list-id)
        content-type (get-in req [:representation :media-type])
        json-response (formatters/list-json-serializer list-id list-contents)]
    (case content-type
      "application/json" json-response
      "text/html" (formatters/generate-application-template json-response))))

(defn handle-put
  [req]
  (let [list-id (get-in req [:request :list-id])
        list-ref (get-in req [:request :state])
        body (slurp (get-in req [:request :body]))
        data (formatters/deserializer body)]
    (do
      (model/put-list! list-ref list-id (get-in data [:list :items]))
      true)))

(def resource (liberator/resource :handle-ok handle-ok
                                  :available-media-types ["text/html"
                                                          "application/json"]
                                  :method-allowed? (liberator/request-method-in :get :put)
                                  :put! handle-put))
