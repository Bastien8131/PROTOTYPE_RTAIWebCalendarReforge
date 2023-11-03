import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [HttpClientModule]
})
export class AppComponent {
  constructor(private httpClient: HttpClient) {
  }

  serverMessage = this.httpClient.get<{message: string}>("demo/angular/api/message");
  messageJson: any = null;

  ngOnInit() {
    this.httpClient.get<{ message: string }>("demo/angular/api/message")
        .subscribe(response => {
          let messageString = response.message; // Stockez le message dans la variable serverMessage
          this.messageJson = JSON.parse(messageString);
        });
  }

}
