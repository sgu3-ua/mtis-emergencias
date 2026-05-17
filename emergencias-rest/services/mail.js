const nodemailer = require('nodemailer');

// Configurar transporte SMTP apuntando a FakeSMTP
const transporter = nodemailer.createTransport({
  host: process.env.SMTP_HOST || "localhost",
  port: process.env.SMTP_PORT || 25,
  secure: false, // FakeSMTP no usa TLS
  tls: {
    rejectUnauthorized: false
  }
});

function sendMail(options) {
  return transporter.sendMail(options);
}

module.exports = { transporter, sendMail };