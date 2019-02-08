# k8s-workshop

## Initial setup:

* Clone this repository (`git clone https://github.com/bashburn-signalfx/k8s-workshop`)
* `kubectl apply -f deployments/preso-deploy.yaml`
* If using minikube, `minikube service list` to get the URL of the presentation

## To add the SignalFx agent:
* Add the SignalFx access token (see deployments/apm-assets-3/agent/README.md)
  ```
  kubectl create secret generic --from-literal access-token=YOUR_TOKEN signalfx-agent
  ```
* Create a service account:
  ```
  kubectl apply -f deployments/apm-assets-3/agent/serviceaccount.yaml
  ```
* Create cluster role:
  ```
  kubectl apply -f deployments/apm-assets-3/agent/clusterrole.yaml
  ```
* Create cluster role binding:
  ```
  kubectl apply -f deployments/apm-assets-3/agent/clusterrolebinding.yaml
  ```
* Update the config map: `deployments/apm-assets-3/agent/configmap.yaml`
  - change `kubernetes_cluster` to something unique
  - configure any plugins that are needed
* Apply the config map:
  ```
  kubectl apply -f deployments/apm-assets-3/agent/configmap.yaml
  ```
* Apply the daemonset:
  ```
  deployments/apm-assets-3/agent/daemonset.yaml
  ```
* Verify that the agent is running by running `kubectl get pods` and verify that there is a SignalFx pod running
* Examine logs to verify that everything is correct: `kubectl logs POD_NAME`
* Verify that data is being sent to SignalFx on the Kubernetes dashboards
