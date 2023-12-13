from fastapi import FastAPI, HTTPException, Depends, status
from pydantic import BaseModel
from typing import Annotated
import models
from database import engine, SessionLocal
from sqlalchemy.orm import Session

app = FastAPI()
models.Base.metadata.create_all(bind=engine)

class AppCreate(BaseModel):
    package_name: str
    app_name: str
    version_code: int
    version_name: str
    file_size: int
    permissions: list
    is_system_app: bool
    is_malicious: bool
    threat_category: str = None
    static_analysis_results: str = None
    dynamic_analysis_results: str = None

def get_db():
    db=SessionLocal()
    try:
        yield db
    finally:
        db.close() 

db_dependency = Annotated[Session, Depends(get_db)]

# Endpoint to create a new app entry
@app.post("/apps/", response_model=dict)
async def create_app(app_data: AppCreate, db: Session = Depends(get_db)):
    # Convert Pydantic model to SQLAlchemy model
    db_app = models.AppDBModel(**app_data.dict())

    # Store the data in the database
    db.add(db_app)
    db.commit()
    db.refresh(db_app)

    return {"message": "App created successfully", "app_id": db_app.id}