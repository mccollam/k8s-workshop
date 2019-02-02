import express from 'express';
import signalfx from 'signalfx';

const app = express();
const port = 3000;
const client = signalfx.Ingests(process.env.SFX_TOKEN);

app.get('/healthz', (req, res) => {
  client.send({
    counters : [
      {
        metric : 'k8ws.express.path.healthz.calls',
        value : 1,
        timestamp : Date.now(),
        dimensions : {
          'app' : 'k8ws-nodejs-express',
        },
      },
    ],
    cumulative_counters : [
      {
        metric : 'k8ws.express.path.healthz.calls_cumulative',
        value : 1,
        timestamp : Date.now(),
        dimensions : {
          'app' : 'k8ws-nodejs-express',
        },
      },
    ],
  });
  res.send('OK');
});

app.listen(port, () => { console.log(`Example listening on port ${port}!`); });

