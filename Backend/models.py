from sqlalchemy import Column, Integer, String, Boolean, Text
from database import Base
from pydantic import BaseModel



class AppDBModel(Base):
    __tablename__ = "apps"

    id = Column(Integer, primary_key=True, index=True)
    package_name = Column(String, index=True)
    app_name = Column(String)
    version_code = Column(Integer)
    version_name = Column(String)
    file_size = Column(Integer)
    permissions = Column(Text)
    is_system_app = Column(Boolean)
    is_malicious = Column(Boolean)
    threat_category = Column(String, nullable=True)
    static_analysis_results = Column(Text, nullable=True)
    dynamic_analysis_results = Column(Text, nullable=True)