FROM node:18-alpine

WORKDIR /usr/src/app

# Install dependencies
COPY package*.json ./
RUN npm install --only=production

# Copy app source
COPY . .

EXPOSE 8080

CMD ["node", "index.js"]
